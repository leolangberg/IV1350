package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseConnectionException;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import se.kth.IV1350.progExe.integration.external.Exceptions.InvalidIdentifierException;
import se.kth.IV1350.progExe.model.DTO.ReceiptDTO;

/**
 * The ExternalAccountingSys class is responsible for handling all communication
 * with the external accounting system.
 * 
 * Contains methods for generating new unique IDs for sales, logging
 * receipts, and managing the accounting system database.
 */
public class ExternalAccountingSys {

    /**
     * Uses GoF 'Singleton' Pattern to create a singular static instance
     * of the AccountingSystem Database.
     */
    private static final AccountingSysDatabase DATABASE = new AccountingSysDatabase();

    /**
     * The call method for using the 'Singleton' database.
     * @return Database Instance (singleton).
     * @throws DatabaseConnectionException in case connection to database fails.
     */
    public static AccountingSysDatabase databaseInstance() throws DatabaseConnectionException { 
        if(DATABASE.databaseConnection != true) {
            throw new DatabaseConnectionException("No connection to Database.");
        }
        return DATABASE; 
    }

    /**
     * Constructs a new ExternalAccountingSys object.
     */
    public ExternalAccountingSys() {}

    /**
     * Generates a new unique ID for a sale.
     *
     * This method retrieves a new unique index from the accounting system database.
     * @return An integer representing the new unique ID.
     * @throws DatabaseExcpetion in case call to database fails.
     */
    public int newID() throws DatabaseException { 
        try{
            return databaseInstance().newIndex(); 
        } catch(DatabaseException dbe) {
            throw new DatabaseException(dbe.getMessage(), dbe);
        }
    }
    
    /**
     * Logs a receipt in the accounting system.
     * 
     * This method adds a receipt to the accounting system database.
     * @param receiptDTO The receipt to be logged.
     * @throws DatabaseExcpetion in case call to database fails.
     */
    public void logReceipt(ReceiptDTO receiptDTO) throws DatabaseException { 
        try{
            databaseInstance().addReceipt(receiptDTO); 
        } catch(DatabaseConnectionException dbe) {
            throw new DatabaseException(dbe.getMessage(), dbe);
        }
    }




    
    /**
     * Accounting System Database ('singleton', thus 'static').
     */
    public static class AccountingSysDatabase {

        public linkedListStruct receiptlog;
        private boolean databaseConnection;

        /**
         * This constructor initializes the AccountingSysDatabase with a new
         * linkedListStruct for holding the Receipts.
         */
        public AccountingSysDatabase() {
            this.receiptlog = new linkedListStruct(); 
            this.databaseConnection = true;
        }

        /**
         * Generates a new unique index for a receipt.
         * @return An integer representing the new unique index.
         */
        public int newIndex() { return receiptlog.amountOfReceipts + 1; }

        /**
         * Adds a receipt to the receipt log.
         * 
         * This method adds a receipt to the receipt log using the sale ID from the
         * receipt sale as the key.
         * @param receiptDTO The receipt to be added to the log.
         */
        public void addReceipt(ReceiptDTO receiptDTO) {
            receiptlog.add(receiptDTO.getReceiptSale().getSaleID(), receiptDTO);
        }

         /**
         * Finds specific ReceiptDTO based on index.
         * 
         * @param index of ReceiptDTO inside database.
         * @return ReceiptDTO stored.
         * @throws DatabaseException if database call fails.
         */
        public ReceiptDTO lookupReceipt(int index) throws InvalidIdentifierException {
            try {
                return receiptlog.lookup(index);
            } catch(InvalidIdentifierException ide) {
                throw new InvalidIdentifierException(ide.getMessage(), ide);
            }
        }

          /**
         * Changes state of connectivity to database. (For testing purposes, see Seminar Task 4)
         * @param connection true or false.
         */
        public void setDatabaseConnection(boolean connection) {
            this.databaseConnection = connection;
        }

        /**
         * Utility Linked List Struct
         * 
         * Designed as a simple Linked List data structure for dynamically
         * storing receipts.
         */
        public class linkedListStruct {

            int amountOfReceipts;
            Node first;

            /**
             * This constructor initializes the linkedListStruct with a null first node.
             */
            public linkedListStruct() {
                amountOfReceipts = 0;
                first = null;
            }

            /**
             * Retrieves the length of the linked list.
             * @return An integer representing the length of the linked list.
             */
            public int length() {
                Node current = first;
                int length = 0;
                while (current != null) {
                    length++;
                    current = current.next;
                }
                return length;
            }

            /**
             * Node class represents the Objects stored inside the Linked List.
             */
            private class Node {

                int index;
                ReceiptDTO receiptDTO;
                Node next;

                /**
                 * Constructs a new Node object.
                 * 
                 * This constructor initializes the Node with the provided index, receiptDTO,
                 * and next node.
                 *
                 * @param index      The index of the node.
                 * @param receiptDTO The receiptDTO to be stored in the node.
                 * @param next       The next node in the linked list.
                 */
                public Node(int index, ReceiptDTO receiptDTO, Node next) {
                    this.index = index;
                    this.receiptDTO = receiptDTO;
                    this.next = next;
                }
            }

            /**
             * Adds a new node to the linked list.
             * 
             * This method creates a new node with the provided index and receiptDTO and
             * adds it to the front of the linked list.
             *
             * @param index      The index of the new node.
             * @param receiptDTO The receiptDTO to be stored in the new node.
             */
            public void add(int index, ReceiptDTO receiptDTO) {

                Node n = new Node(index, receiptDTO, null);

                if (first == null)
                    first = n;

                n.next = first;
                first = n;

                amountOfReceipts++;
            }

            /**
             * Looks up a receipt in the linked list based on the provided index.
             *
             * @param index The index of the node to find.
             * @return The receiptDTO stored in the found node, or null if the index was not
             *         found.
             * @throws InvalidIdentifierException in case there is no object on given index.
             */
            public ReceiptDTO lookup(int index) throws InvalidIdentifierException {

                Node n = first;
                while (n.index != index) {
                    if (n.next == null) {
                        throw new InvalidIdentifierException("object on index: " + index + " does not exist in database");
                    }
                    n = n.next;
                }
                return n.receiptDTO;
            }
        }
    }

}
