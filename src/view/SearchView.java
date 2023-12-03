package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searching.SearchController;
import interface_adapter.searching.SearchState;
import interface_adapter.searching.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Search Stock";
    private final SearchController searchController;

    private final SearchViewModel searchViewModel;

    private JTextField stocknameInputField = new JTextField(15);


    private final JButton goBack;


    private JButton stock1 = new JButton();

    private JButton stock2 = new JButton();

    private JButton stock3 = new JButton();

    private JButton stock4 = new JButton();

    private JButton stock5 = new JButton();
    private JPanel panel1;
    private JLabel Search;


    public SearchView(SearchController controller, SearchViewModel searchViewModel, ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);


        LabelTextPanel stocknameInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.STOCKNAME_LABEL), stocknameInputField);

        JPanel buttons = new JPanel();
        goBack = new JButton(SearchViewModel.GOBACK);
        buttons.add(goBack);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);


        stock1.setText("");
        stock1.setAlignmentX(Component.CENTER_ALIGNMENT);
        stock2.setText("");
        stock2.setAlignmentX(Component.CENTER_ALIGNMENT);
        stock3.setText("");
        stock3.setAlignmentX(Component.CENTER_ALIGNMENT);
        stock4.setText("");
        stock4.setAlignmentX(Component.CENTER_ALIGNMENT);
        stock5.setText("");
        stock5.setAlignmentX(Component.CENTER_ALIGNMENT);


        goBack.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(goBack)){
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        stock1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(stock1)){
                            SearchState state = searchViewModel.getSearchState();
                            state.setSearchName(stock1.getText());
                            viewManagerModel.setActiveView(stockViewModel.getViewName);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        stock2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(stock2)){
                            SearchState state = searchViewModel.getSearchState();
                            state.setSearchName(stock2.getText());
                            viewManagerModel.setActiveView(stockViewModel.getViewName);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        stock3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(stock3)){
                            SearchState state = searchViewModel.getSearchState();
                            state.setSearchName(stock3.getText());
                            viewManagerModel.setActiveView(stockViewModel.getViewName);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        stock4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(stock4)){
                            SearchState state = searchViewModel.getSearchState();
                            state.setSearchName(stock4.getText());
                            viewManagerModel.setActiveView(stockViewModel.getViewName);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        stock5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(stock5)){
                            SearchState state = searchViewModel.getSearchState();
                            state.setSearchName(stock5.getText());
                            viewManagerModel.setActiveView(stockViewModel.getViewName);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );


        stocknameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getSearchState();
                        String text = stocknameInputField.getText() + e.getKeyChar();
                        currentState.setSearchName(text);
                        searchViewModel.setState(currentState);
                        SearchState current = searchViewModel.getSearchState();
                        searchController.search(current.getSearchName());
                        HashMap<String, String> storedStock = searchViewModel.getSearchState().getStoredStocks();
                        Set<String> stockSet = storedStock.keySet();
                        List<String> stockList = stockSet.stream().toList();
                        stock1.setText(stockList.get(0));
                        stock2.setText(stockList.get(1));
                        stock3.setText(stockList.get(2));
                        stock4.setText(stockList.get(3));
                        stock5.setText(stockList.get(4));
                   }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    this.add(stocknameInfo);
    this.add(stock1);
    this.add(stock2);
    this.add(stock3);
    this.add(stock4);
    this.add(stock5);
    this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click" + e.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(searchViewModel)) {
            SearchState state = (SearchState) evt.getNewValue();

            if (state.getSearchError() != null) {
                JOptionPane.showMessageDialog(this, state.getSearchError());
            }
                }
            }
        }
