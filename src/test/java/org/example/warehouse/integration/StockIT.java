package org.example.warehouse.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.warehouse.stock.Stock;
import org.example.warehouse.stock.StockRepository;
import org.example.warehouse.stock.stockCommand.UpdateStockCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StockIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanDB() {
        stockRepository.deleteAll();
    }


    @Test
    void shouldGetStockById() throws Exception {
        Stock stock = new Stock(5L, 15);
        stockRepository.save(stock);

        var mvcResult = mockMvc.perform(get("/stock/{id}", 5L)
                        .with(httpBasic("user", "123")) // dodane uwierzytelnienie
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        Stock returnedStock = objectMapper.readValue(json, Stock.class);

        assertThat(returnedStock).isNotNull();
        assertThat(returnedStock.getProductId()).isEqualTo(stock.getProductId());
        assertThat(returnedStock.getQuantity()).isEqualTo(stock.getQuantity());
    }



    @Test
    void shouldUpdateStock() throws Exception {
        Stock stock = new Stock(3L, 10);
        stockRepository.save(stock);

        UpdateStockCommand updateCmd = new UpdateStockCommand(3L, 5, null, true);

        mockMvc.perform(put("/stock/")
                        .with(httpBasic("admin", "123")) // admin może modyfikować
                        .content(objectMapper.writeValueAsString(updateCmd))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Stock updatedStock = stockRepository.findById(3L).orElseThrow();
        assertThat(updatedStock.getQuantity()).isEqualTo(15); // 10 + 5
    }


    @Test
    void shouldDeleteStock() throws Exception {
        Stock stock = new Stock(7L, 30);
        stockRepository.save(stock);


        mockMvc.perform(delete("/stock/7")
                        .with(httpBasic("admin", "123")))
                .andExpect(status().isOk());

        boolean exists = stockRepository.existsById(7L);
        assertThat(exists).isFalse();
    }



}
