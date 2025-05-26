package org.example.warehouse.stock;

import io.swagger.v3.oas.annotations.Operation;
import org.example.warehouse.stock.stockCommand.UpdateStockCommand;
import org.example.warehouse.stock.stockDto.StockDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockFacade stockFacade;

    public StockController(StockFacade stockFacade) {
        this.stockFacade = stockFacade;

    }


    @GetMapping(value = "/")
    @Operation(summary = "Return all Stocks", description = "Return a list of all stocks from DB")
    public List<StockDto> getStock() {
        return stockFacade.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Stock", description = "Get stock by id")
    public StockDto getStock(@PathVariable Long id) {
        return stockFacade.getStockByProductId(id);
    }

    @PutMapping("/")
    @Operation(summary = "Update stock ",
            description = "Update quantity, reserved quantity or increment quantity," +
                    " reserved quantity it depends of flag value ")
    public void updateStock(@RequestBody UpdateStockCommand cmd) {
        stockFacade.updateStock(cmd);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a stock ", description = "Delete a stock")
    public void deleteStock(@PathVariable Long id) {
        stockFacade.deleteStock(id);

    }


}
