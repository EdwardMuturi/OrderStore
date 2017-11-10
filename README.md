# OrderStore
Android Content Provider App

To Do

    [] Create two android apps
    [] backend app - content provider
    
         [] Create a db that has four tables with columns that you choose which must make sense.
         
              [] Customers Table-store customer details
              [] Products Table-store product details
              [] Orders Table -store the products order summary made by a customer.
              [] OrderLines Table - store each product order details made by a customer.i.e. a customer may make an order of five different/same products.Orders table will have general details of the order eg customer_id,total_amount,number_of_products etc while OrderLines Table will have details of each of the products ordered by the customer plus the orders_id to relate it to the Orders Table.

         [] Create the content provider giving content provider uris for each table that will be used to send,fetch and update data in these tables from the Front UI which is the other app.

         [] Each of these tables need some user access level checked as not all users will have access to all tables in the database.eg not every user will be able to edit/add details of a product but all can view.

    [] Frontend UI
    [] This is the app that a user interacts with.
    [] The front UI will be a widget.(Design as much as you want)
    [] Have three parts
         [] Customers-part for viewing saved customers,editing their details,adding new customers
         [] Products - part for viewing saved products,editing their details,adding new products.
         [] Orders - part where a user can select products a customer wants to order,select the customer and create order.

    NB:
    These two applications will be in communication
    The front UI will be using the uris from the backend app to create,update,fetch details needed on the front ui.
