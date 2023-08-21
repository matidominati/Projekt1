package seller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerService sellerService = new SellerService();

        Seller seller1 = new Seller("Andrzej", "Nowak", "0000000000", 5000, ProductsType.FRUIT, 10, 6);
        Seller seller2 = new Seller("Mariusz", "Kowalski", "1000000001", 6660.3, ProductsType.FRUIT, 5, 1);
        Seller seller3 = new Seller("Jan", "Zieba", "2000000002", 8000, ProductsType.VEGETABLE, 80, 20);
        Seller seller4 = new Seller("Janina", "Zieba", "3000000003", 15000, ProductsType.VEGETABLE, 80, 20);


        System.out.println("--------DODAWANIE SPRZEDAWCÓW------");
        sellerService.addSeller(seller1);
        sellerService.addSeller(seller2);
        sellerService.addSeller(seller1);
        sellerService.addSeller(seller3);
        System.out.println("");

        System.out.println("-----WYŚWIETLANIE INFO O SPRZEDAWCACH------");

        sellerService.showSeller("0000000000");
        sellerService.showSeller("101");

        System.out.println("");
        System.out.println("-------ZWRACANIE SPRZEDAWCY PO IMIENIU I NAZWISKU---------");

        sellerService.getSellerByName("Andrzej", "Nowak");

        System.out.println(sellerService.getSellerbyPESEL("0000000000"));
        System.out.println(sellerService.getSellerbyPESEL("101"));

        System.out.println("");
        System.out.println("--------------USUWANIE SPRZEDAWCÓW------------------");

        sellerService.deleteSeller("0000000000");
        sellerService.deleteSeller("101");
        System.out.println("");
        System.out.println("---------DODAWANIE PRODUKTÓW--------");

        Product product1 = new Product("Jabłko", ProductsType.FRUIT, 0.99);
        Product product2 = new Product("Gruszka", ProductsType.FRUIT, 0.94);
        Product product3 = new Product("Marchewka", ProductsType.VEGETABLE, 0.5);
        Product product4 = new Product("Pomidor", ProductsType.VEGETABLE, 2.85);

        sellerService.addProduct(product1, seller1);
        sellerService.addProduct(product2, seller1);
        sellerService.addProduct(product1, seller2);
        sellerService.addProduct(product2, seller2);
        sellerService.addProduct(product2, seller3);
        sellerService.addProduct(product3, seller3);
        sellerService.addProduct(product3, seller4);
        sellerService.addProduct(product4, seller4);
        System.out.println("");
        System.out.println("-------------ZASTĄPIENIE AKTUALNYCH PRODUKTÓW NOWYMI PRODUKTAMI------------------------");
        List<Product> lista = new ArrayList<>();
        Product p1 = new Product("Ogórek", ProductsType.VEGETABLE, 1.98);
        Product p2 = new Product("Kapusta", ProductsType.VEGETABLE, 2);
        Product p3 = new Product("Śliwka", ProductsType.FRUIT, 5.66);
        Product p4 = new Product("Jagoda", ProductsType.FRUIT, 11.88);

        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        lista.add(p4);

        sellerService.addProductList(lista, seller2);
        sellerService.addProductList(lista, seller4);

        System.out.println("");
        System.out.println("------------USUWANIE PRODUKTÓW Z LISTY PRODUKTÓW-------------------------");
        sellerService.sell(p1, seller1);
        sellerService.sell(p1, seller3);
        sellerService.sell(p1, seller4);

        System.out.println("");
        System.out.println("------------SPRZEDANIE PRODUKTU I ZWRÓCENIE JEGO CENY--------------------");

        double price = sellerService.getPriceOfProductForSeller("1000000001", "Kapusta");
        System.out.println("Cena produktu: " + price + " PLN");


        Market market = new Market(sellerService, BigDecimal.ZERO);

        System.out.println("");
        System.out.println("----------SPRZEANIE PRODUKTU I DODANIE WARTOSCI DO POLA EARNINGS-------");
        market.sellProduct("2000000002", product2);
        System.out.println("Wartość earnings: " + market.getEarnings() + " " + " PLN");
        market.sellProduct("2000000002", product3);
        System.out.print("Wartość earnings: " + market.getEarnings() + " " + " PLN");
        System.out.println(" (" + product2.getPrice() + " + " + product3.getPrice() + ")" );

//        System.out.println("");
//        System.out.println("----SPRZEDANIE WSZYSTKICH OWOCOW OD WSZYSTKICH SPRZEDAWCOW-----");
//        market.sellAllFruits();
//
//        System.out.println("");
//        System.out.println("----SPRZEDANIE WSZYSTKICH WARZYW OD WSZYSTKICH SPRZEDAWCOW-----");
//        market.sellAllVegetables();
//
//        System.out.println("");
//        System.out.println("----SPRZEDANIE WSZYSTKICH COINS OD WSZYSTKICH SPRZEDAWCOW-----");
//        market.sellAllCoins();
//
//        System.out.println("");
//        System.out.println("----SPRZEDANIE WSZYSTKICH TOYS OD WSZYSTKICH SPRZEDAWCOW-----");
//        market.sellAllToys();


        market.stocktaking();

        market.stocktakingWithArguments(ProductsType.VEGETABLE);

        market.stocktakingFruits();

        System.out.println("Imiona istniejących sprzedawców:");
        market.printAllNamesOfSellers();
        System.out.println("");
        System.out.println("Imiona sprzedawców z >0 produktów:");

        market.printNamesOfSellersWithoutNullProducts();

        System.out.println("");
        System.out.println("Imiona sprzedawców z więcej niż 1 produktem: ");
        market.printNamesOfSellersWithSomeProducts(1);

        market.getAllSellersWithoutNullProducts();
        market.getAllSellersWith(ProductsType.FRUIT);
        market.getAllSellersWithOnlyFruits();
        market.getAllSellersWithOnlyVegetables();
        market.getNProductList(2,ProductsType.VEGETABLE);


        GlobalMarket globalMarket = new GlobalMarket();

        globalMarket.getAllProucts();
        globalMarket.sellAllProducts(ProductsType.VEGETABLE);


    }
}
