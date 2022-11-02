package com.csse.procureapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.csse.models.Order;
import com.csse.models.SiteManager;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.csse.procureapp", appContext.getPackageName());
    }

    @Test
    public void OrderIdTest() {

        Order order=new Order("ID", "ItemName", "Status", "Delivered" , "Address", "Quantity", "UID", "Date");
        assertEquals("ID", order.getId());
    }

    @Test
    public void OrderItemTest() {

        Order order=new Order("ID", "ItemName", "Status", "Delivered" , "Address", "Quantity", "UID", "Date");
        assertEquals("ItemName", order.getItem());

    }

    @Test
    public void OrderStatusTest() {

        Order order=new Order("ID", "ItemName", "Status", "Delivered" , "Address", "Quantity", "UID", "Date");
        assertEquals("Status", order.getStatus());

    }
    @Test
    public void OrderDeliveredTest() {

        Order order=new Order("ID", "ItemName", "Status", "Delivered" , "Address", "Quantity", "UID", "Date");
        assertEquals("Delivered", order.getDelivered());

    }

    @Test
    public void OrderAddressTest() {

        Order order=new Order("ID", "ItemName", "Status", "Delivered" , "Address", "Quantity", "UID", "Date");
        assertEquals("Address", order.getAddress());

    }

    @Test
    public void OrderQuantityTest() {

        Order order=new Order("ID", "ItemName", "Status", "Delivered" , "Address", "Quantity", "UID", "Date");
        assertEquals("Quantity", order.getQuantity());

    }

    @Test
    public void OrderUIDTest() {

        Order order=new Order("ID", "ItemName", "Status", "Delivered" , "Address", "Quantity", "UID", "Date");
        assertEquals("UID", order.getUid());

    }

    @Test
    public void OrderDateTest() {

        Order order=new Order("ID", "ItemName", "Status", "Delivered" , "Address", "Quantity", "UID", "Date");
        assertEquals("Date", order.getExpectedDate());

    }

    @Test
    public void SiteManagerNameTest() {

        SiteManager siteManager =new SiteManager("Name", "Email");
        assertEquals("Name", siteManager.getName());

    }

    @Test
    public void SiteManagerEmailTest() {

        SiteManager siteManager =new SiteManager("Name", "Email");
        assertEquals("Email", siteManager.getEmail());

    }



}