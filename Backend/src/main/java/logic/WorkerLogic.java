package logic;

import models.Worker;

import java.util.Optional;

public class WorkerLogic {
    public Optional<Worker> loginHandler(Worker worker) {
         if(worker.getEmail().equals("Karin22@hugo.se") && worker.getPassword().equals("111")) { //NcTZUZCJqBib1fCpMh76GgoBG7VklS_yZ6nfs5TscIQ || NcTZUZCJqBib1fCpMh76GgoBG7VklS_yZ6nfs5TscIQ
            return Optional.of(worker);
        } else {
            return Optional.empty();
        }
    }
}
