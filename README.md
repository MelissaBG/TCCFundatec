# TCC
USER
POST - http://localhost:8080/users/login
POST - http://localhost:8080/users/salvar
GET - http://localhost:8080/users/buscar/{id}
PUT - http://localhost:8080/users/atualizar/{id}
DELETE - http://localhost:8080/users/deletar/{id}
GET - http://localhost:8080/users/buscar/{id}

http://localhost:8080/medications/{userId}/create
http://localhost:8080/medications/{userId}/update
http://localhost:8080/medications/findAll
http://localhost:8080/medications/findMedicationUserById/{id}
http://localhost:8080/medications/delete/{id}
http://localhost:8080/medications/updateExistingMedication/{medicationUserId}/userMedication/{medicationId}
http://localhost:8080/medications/addMedicationsToList/{medicationUserId}/userMedication/add
http://localhost:8080/medications/removeMedicationFromList/{medicationUserId}/userMedication/remove
http://localhost:8080/medications/updateMedicationInList/{medicationUserId}/userMedication/update
