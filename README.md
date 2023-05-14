# TCC
USER
POST - http://localhost:8080/users/login
POST - http://localhost:8080/users/save
GET - http://localhost:8080/users//{id}
PUT - http://localhost:8080/users/update/{id}
DELETE - http://localhost:8080/users/delete/{id}

MEDICATION USER
http://localhost:8080/medication_users/add_medications/{medicationUserId}
http://localhost:8080/medication_users/removeMedicationFromUser/{userName}/medications/{medicationId}
http://localhost:8080/medicationUsers/updateMedicationFromUser/{userName}/medications
http://localhost:8080/medication_users/{id}
http://localhost:8080/medication_users/save
http://localhost:8080/medication_users/update/{id}
http://localhost:8080/medication_users/delete/{id}

