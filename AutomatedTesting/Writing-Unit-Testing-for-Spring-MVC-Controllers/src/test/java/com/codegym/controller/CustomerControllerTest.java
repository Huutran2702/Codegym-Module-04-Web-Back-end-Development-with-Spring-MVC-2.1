package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import com.codegym.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebAppConfiguration
public class CustomerControllerTest {

    private ICustomerService customerService = Mockito.mock(CustomerServiceImpl.class);

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }

    @Test
    public void find_All_Test() throws Exception{
        Customer first = new Customer(1,"Nguyen","Tung");
        Customer second = new Customer(2,"Hoang","Nam");
        when(customerService.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc
                .perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/list"))
                .andExpect(forwardedUrl("/customer/list"))
                .andExpect(model().attribute("customers", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("firstName", is("Nguyen")),
                                hasProperty("lastName", is("Tung"))
                        )
                )))
                .andExpect(model().attribute("customers", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("firstName", is("Hoang")),
                                hasProperty("lastName", is("Nam"))
                        )
                )));
        verify(customerService, times(1)).findAll();
        verifyNoMoreInteractions(customerService);

    }

    @Test
    public void find_By_Id_Test() throws Exception {
        Customer found = new Customer(1,"Nguyen","Tung");
        when(customerService.findById(1)).thenReturn(found);

        mockMvc
                .perform(get("/customer/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/detail"))
                .andExpect(forwardedUrl("/customer/detail"))
                .andExpect(model().attribute("customer", hasProperty("id", is(1))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is("Nguyen"))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is("Tung"))));

        verify(customerService, times(1)).findById(1);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void add_Test() throws Exception {
        Customer added = new Customer(1,"Nguyen","Tung");
        when(customerService.add(isA(Customer.class))).thenReturn(added);

        mockMvc
                .perform(post("/customer/save")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Nguyen")
                        .param("lastName", "Tung")
                        .sessionAttr("customer", new Customer())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/create"));

        ArgumentCaptor<Customer> formObjectArgument = ArgumentCaptor.forClass(Customer.class);
        verify(customerService, times(1)).add(formObjectArgument.capture());
        verifyNoMoreInteractions(customerService);

        Customer formObject = formObjectArgument.getValue();
        assertEquals(formObject.getFirstName(), "Nguyen");
        assertEquals(formObject.getLastName(), "Tung");
    }
}
