package edu.iis.mto.testreactor.reservation;

import edu.iis.mto.testreactor.money.Money;
import edu.iis.mto.testreactor.offer.Discount;
import edu.iis.mto.testreactor.offer.DiscountPolicy;
import edu.iis.mto.testreactor.offer.Offer;
import edu.iis.mto.testreactor.offer.OfferItem;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationTest {

    private static final String CLIENT_NAME="andrzej";
    private static final String PRODUCT_NAME="keyboard";
    private static  final double DENOMINATION=100;
    private Id id;
    private Id productId;
    private Reservation.ReservationStatus status;
    private ClientData clientData;
    private Date createDate;
    private Reservation reservation;
    private Money price;
    private ProductData productData;
    @Mock
    DiscountPolicy discountPolicy;

    private ReservationItem reservationItem;

    Discount discount;
    Product product;
    ReservationItem item;
    Offer offer;
    @BeforeEach
    void setUp(){
        id=new Id();
        id.generate();
        clientData=new ClientData(id,CLIENT_NAME);
        price=new Money(DENOMINATION);
        createDate=new Date();
        reservation=new Reservation(id,status,clientData,createDate);
        //discountPolicy.applyDiscount(product, item.getQuantity(), product.getPrice());
        offer=reservation.calculateOffer(discountPolicy);

        productId=new Id();

        productData=new ProductData(productId,price, PRODUCT_NAME, ProductType.STANDARD,createDate);
    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

    @Test
    void calculateOfferShouldReturnListsWithZeroElements(){

        assertEquals(0,offer.getAvailabeItems().size());
        assertEquals(0,offer.getUnavailableItems().size());
    }
    @Test
    void  calculateOfferShouldReturnListsWithTwoAvaiableProduct(){
//        OfferItem offerItem=new OfferItem(productData,1);
//        OfferItem offerItem2=new OfferItem(productData,1);
        Product product=new Product(productId,price,PRODUCT_NAME,ProductType.STANDARD,ProductStatus.ACTIVE);
//        ReservationItem reservationItem=new ReservationItem(product,1);
//        ReservationItem reservationItem2=new ReservationItem(product,1);

        reservation.addNew(product,1);
        reservation.addNew(product,1);
        offer=reservation.calculateOffer(discountPolicy);

        assertEquals(2,offer.getAvailabeItems().size());
        assertEquals(0,offer.getUnavailableItems().size());
    }

    @Test
    void  calculateOfferShouldReturnListsWithTwoUnvaiableProduct(){
        Product product=new Product(productId,price,PRODUCT_NAME,ProductType.STANDARD,ProductStatus.ARCHIVE);

        reservation.addNew(product,1);
        reservation.addNew(product,1);
        offer=reservation.calculateOffer(discountPolicy);

        assertEquals(0,offer.getAvailabeItems().size());
        assertEquals(2,offer.getUnavailableItems().size());
    }

    @Test
    void  calculateOfferShouldReturnListsWithTwoUnvaiableProductandTwoAvaiable(){
        Product product=new Product(productId,price,PRODUCT_NAME,ProductType.STANDARD,ProductStatus.ARCHIVE);
        Product product2=new Product(productId,price,PRODUCT_NAME,ProductType.STANDARD,ProductStatus.ACTIVE);

        reservation.addNew(product,1);
        reservation.addNew(product,1);
        reservation.addNew(product2,1);
        reservation.addNew(product2,1);
        offer=reservation.calculateOffer(discountPolicy);

        assertEquals(2,offer.getAvailabeItems().size());
        assertEquals(2,offer.getUnavailableItems().size());
    }
    @Test
    void  calculateOfferShouldReturnListsWithOneUnvaiableProductandOneAvaiable(){
        Product product=new Product(productId,price,PRODUCT_NAME,ProductType.STANDARD,ProductStatus.ARCHIVE);
        Product product2=new Product(productId,price,PRODUCT_NAME,ProductType.STANDARD,ProductStatus.ACTIVE);

        reservation.addNew(product,1);
        reservation.addNew(product2,1);
        offer=reservation.calculateOffer(discountPolicy);

        assertEquals(1,offer.getAvailabeItems().size());
        assertEquals(1,offer.getUnavailableItems().size());
    }
}