# TCC
USER
POST - http://localhost:8080/users/login
POST - http://localhost:8080/users/salvar
GET - http://localhost:8080/users/buscar/{id}
PUT - http://localhost:8080/users/editar/{id}
DELETE - http://localhost:8080/users/deletar/{id}
GET - http://localhost:8080/users/buscar/{id}

MEDICATION
POST - http://localhost:8080/medications/save
GET - http://localhost:8080/medications/getAllMedications
PUT - http://localhost:8080/medications/updateMedication/{id}
DELETE - http://localhost:8080/medications/deleteMedication/{id}
GET - http://localhost:8080/medications/getMedicationById/{id}

LIST MEDICATION
POST - http://localhost:8080/medications/addMedicationsToList/{id}
DELETE - http://localhost:8080/medications/removeMedicationFromList/{id}
PUT - http://localhost:8080/medications/updateMedicationInList/{id}