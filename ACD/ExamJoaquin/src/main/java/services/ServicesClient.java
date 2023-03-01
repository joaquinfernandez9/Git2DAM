package services;

import dao.DaoClient;
import dao.DaoPurchase;
import jakarta.inject.Inject;
import modelo.hibernate.Client;

public class ServicesClient {
        private final DaoClient daoClient;
        private final DaoPurchase daoPurchase;

        @Inject
        public ServicesClient(DaoClient daoClient, DaoPurchase daoPurchase) {
            this.daoClient = daoClient;
            this.daoPurchase = daoPurchase;
        }

        public String deleteClient(Client client, boolean delete) {
            String response = "client deleted";
            if(daoPurchase.getAllPurchases(client).isEmpty()){
                daoClient.delete(client);
            } else if (delete){
                daoClient.delete(client);
            } else {
                response = null;
            }
            return response;
        }
}
