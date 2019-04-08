package com.demo.induction.entity;

public enum DataType {
    TRANSACTION {
        @Override
        public String getXMLNodeName() {
            return "Transaction";
        }

        @Override
        public String getDescription() {
            return "Transaction should either be 'D' or 'C'";
        }
    }, NARRATION {
        @Override
        public String getXMLNodeName() {
            return "narration";
        }


        @Override
        public String getDescription() {
            return "Narration must be present";
        }
    }, AMOUNT {
        @Override
        public String getXMLNodeName() {
            return "amount";
        }


        @Override
        public String getDescription() {
            return "Amount must be present";
        }
    }, TYPE {
        @Override
        public String getXMLNodeName() {
            return "type";
        }


        @Override
        public String getDescription() {
            return "Type must be present";
        }
    };

    /**
     *
     * @return The name of node in XML file
     */
    public abstract String getXMLNodeName();

    /**
     *
     * @return The description to show in case of error
     */
    public abstract String getDescription();

}