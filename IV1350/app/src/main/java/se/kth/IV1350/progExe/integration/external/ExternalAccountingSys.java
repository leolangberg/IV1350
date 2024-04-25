package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.model.DTO.ReceiptDTO;



public class ExternalAccountingSys {

    public AccountingSysDatabase database;

    public ExternalAccountingSys() {
        this.database = new AccountingSysDatabase();
    }

    /**
     * new original index for a new sale.
     */
    public int newID() {

        return database.newIndex();
    }

    public void logReceipt(ReceiptDTO receiptDTO) {
        database.addReceipt(receiptDTO);   
    }


    /**
     * DATABASE
     */
    public class AccountingSysDatabase {
    

        private linkedListStruct receiptlog;
    
        public AccountingSysDatabase() {
            this.receiptlog = new linkedListStruct();
        }
    
        public int newIndex() {
            return receiptlog.length() + 1;
        }
    
        public void addReceipt(ReceiptDTO receiptDTO) {
            receiptlog.add(receiptDTO.getReceiptSale().getSaleID(), receiptDTO);
        }
        
        /**
         * UTILITY LINKED LIST STRUCT
         */
        private class linkedListStruct {
    
            Node first;
    
            public linkedListStruct() {
    
                first = null;
            }
    
            /**
             * Since latest addition is always at "first" to see length just get first.index
             */
            public int length() {
    
                if (first == null) {
                    return 0;
                }
                return first.index;
            }
    
            private class Node {
    
                int index;
                ReceiptDTO receiptDTO;
                Node next;
    
                public Node(int index, ReceiptDTO receiptDTO, Node next) {
                    this.index = index;
                    this.receiptDTO = receiptDTO;
                    this.next = next;
                }
            }
    
            public void add(int index, ReceiptDTO receiptDTO) {
    
                Node n = new Node(index, receiptDTO, null);
    
                if (first == null)
                    first = n;
    
                // place it in front of first node.
    
                n.next = first;
                first = n;
            }
    
            // might possibly need to be change to return node then another
            // function that returns sale.
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
