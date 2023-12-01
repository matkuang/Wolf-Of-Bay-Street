package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyController;
import interface_adapter.sell.SellController;
import interface_adapter.sell.SellPresenter;
import interface_adapter.sell.SellViewModel;
import entity.CommonUser;
import entity.Stock;
import use_case.sell.SellDataAccessInterface;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInteractor;
import use_case.sell.SellOutputBoundary;
import view.Buy_Sell_View;
import view.SellView;

import javax.swing.*;
import java.io.IOException;

public class SellUseCaseFactory {
    /** Prevent instantiation. */
    private SellUseCaseFactory() {}
    public static SellView create(ViewManagerModel viewManagerModel, SellViewModel sellViewModel,
                                  SellDataAccessInterface userDataAccessObject, CommonUser commonUser,
                                  Stock stock, int amount, String userName){
        try {
            SellController sellController = createSellUseCase(viewManagerModel, sellViewModel, userDataAccessObject, commonUser, stock);
            return new SellView(sellController, sellViewModel, stock.getStockName(), amount, userName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to create");
        }
        return null;
    }

    private static SellController createSellUseCase(ViewManagerModel viewManagerModel, SellViewModel sellViewModel, SellDataAccessInterface userDataAccessObject, CommonUser user, Stock stock) throws IOException{
        SellOutputBoundary sellOutputBoundary = new SellPresenter(viewManagerModel, sellViewModel);
        SellInputBoundary userSellInteractor = new SellInteractor(userDataAccessObject, user, sellOutputBoundary, stock);

        return new SellController(userSellInteractor);
    }
}
