# TCC
USER
POST - http://localhost:8080/users/login
POST - http://localhost:8080/users/salvar
GET - http://localhost:8080/users/buscar/{id}
PUT - http://localhost:8080/users/editar/{id}
DELETE - http://localhost:8080/users/deletar/{id}
GET - http://localhost:8080/users/buscar/{id}

MEDICATION USER
POST - http://localhost:8080/medicationUsers/saveMedicationUser
PUT - http://localhost:8080/medicationUsers/updateMedicationUser/{id}
GET - http://localhost:8080/medicationUsers/getMedicationUserById/{id}
DELETE - http://localhost:8080/medicationUsers/deleteMedicationUserById/{id}

POST - http://localhost:8080/medicationUsers/{medicationUserId}/addMedications
DELETE - http://localhost:8080/medicationUsers/{medicationUserId}/removeMedication
PUT - http://localhost:8080/medicationUsers/{medicationUserId}/updateMedication/{medicationId}
