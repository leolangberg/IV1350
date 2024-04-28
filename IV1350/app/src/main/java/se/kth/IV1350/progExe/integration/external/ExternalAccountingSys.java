package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.model.DTO.ReceiptDTO;



/**
 * The ExternalAccountingSys class is responsible for handling all communication with the external accounting system.
 * 
 * This class contains methods for generating new unique IDs for sales, logging receipts, and managing the accounting system database.
 */
public class ExternalAccountingSys {

    public AccountingSysDatabase database;
    private int lastIndex = 0;

    /**
     * Constructs a new ExternalAccountingSys object.
     * 
     * This constructor initializes the ExternalAccountingSys with a new AccountingSysDatabase.
     */
    public ExternalAccountingSys() {
        this.database = new AccountingSysDatabase();
    }

    /**
     * Generates a new unique ID for a sale.
     * 
     * This method retrieves a new unique index from the accounting system database.
     *
     * @return An integer representing the new unique ID.
     */
    public int newID() {

        return database.newIndex();
    }


    /**
     * Logs a receipt in the accounting system.
     * 
     * This method adds a receipt to the accounting system database.
     *
     * @param receiptDTO The receipt to be logged.
     */
    public void logReceipt(ReceiptDTO receiptDTO) {
        database.addReceipt(receiptDTO);   
    }


    /**
     * DATABASE
     */
    public class AccountingSysDatabase {
    

        public linkedListStruct receiptlog;
    

        
    /**
     * Constructs a new AccountingSysDatabase object.
     * 
     * This constructor initializes the AccountingSysDatabase with a new linkedListStruct for the receipt log.
     */
    public AccountingSysDatabase() {
        this.receiptlog = new linkedListStruct();
    }

    /**
     * Generates a new unique index for a receipt.
     * 
     * This method retrieves the length of the receipt log and adds 1 to generate a new unique index.
     *
     * @return An integer representing the new unique index.
     */
    public int newIndex() {
        lastIndex++;
        receiptlog.length();
        return lastIndex;
    }

    /**
     * Adds a receipt to the receipt log.
     * 
     * This method adds a receipt to the receipt log using the sale ID from the receipt sale as the key.
     *
     * @param receiptDTO The receipt to be added to the log.
     */
    public void addReceipt(ReceiptDTO receiptDTO) {
        receiptlog.add(receiptDTO.getReceiptSale().getSaleID(), receiptDTO);
    }
        

        /**
         * UTILITY LINKED LIST STRUCT
         */
        public class linkedListStruct {
    
            Node first;
    
            /**
             * Constructs a new linkedListStruct object.
             * 
             * This constructor initializes the linkedListStruct with a null first node.
             */
            public linkedListStruct() {
                first = null;
            }

            /**
             * Retrieves the length of the linked list.
             * 
             * This method checks if the first node is null. If it is, it returns 0. Otherwise, it returns the index of the first node.
             *
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
    
            private class Node {
    
                int index;
                ReceiptDTO receiptDTO;
                Node next;
    
                /**
                * Constructs a new Node object.
                * 
                * This constructor initializes the Node with the provided index, receiptDTO, and next node.
                *
                * @param index The index of the node.
                * @param receiptDTO The receiptDTO to be stored in the node.
                * @param next The next node in the linked list.
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
            * This method creates a new node with the provided index and receiptDTO and adds it to the front of the linked list.
            *
            * @param index The index of the new node.
            * @param receiptDTO The receiptDTO to be stored in the new node.
            */
            public void add(int index, ReceiptDTO receiptDTO) {
    
                Node n = new Node(index, receiptDTO, null);
    
                if (first == null)
                    first = n;
    
                // place it in front of first node.
    
                n.next = first;
                first = n;
            }
    
            /**
            * Looks up a receipt in the linked list based on the provided index.
            *
            * @param index The index of the node to find.
            * @return The receiptDTO stored in the found node, or null if the index was not found.
            */
            public ReceiptDTO lookup(int index) {
    
                Node n = first;
                while (n.index != index) {
                    if (n.next == null) { // reached end.
                        return null;
                    }
                    n = n.next;
                }
                return n.receiptDTO;
            }
        }
    }

}
