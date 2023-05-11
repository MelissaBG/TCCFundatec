# TCC
USER
POST - http://localhost:8080/users/login
POST - http://localhost:8080/users/salvar
GET - http://localhost:8080/users/buscar/{id}
PUT - http://localhost:8080/users/editar/{id}
DELETE - http://localhost:8080/users/deletar/{id}
GET - http://localhost:8080/users/buscar/{id}

MEDICATION USER

POST - http://localhost:8080/medicationUsers/{medicationUserId}/addMedications
DELETE - http://localhost:8080/medicationUsers/{medicationUserId}/removeMedication
PUT - http://localhost:8080/medicationUsers/{medicationUserId}/updateMedication/{medicationId}


MEDICATION

http://localhost:8080/medications/createMedication
http://localhost:8080/medications/deleteMedication/{medicationName}
http://localhost:8080/medications/updateMedication/{medicationName}
