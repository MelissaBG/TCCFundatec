# TCC
USER
POST - http://localhost:8080/users/login
POST - http://localhost:8080/users/save
GET - http://localhost:8080/users//{id}
PUT - http://localhost:8080/users/update/{id}
DELETE - http://localhost:8080/users/delete/{id}

MEDICATION USER
http://localhost:8080/medicationUsers/addMedicationToUser/{userName}/medications
http://localhost:8080/medicationUsers/removeMedicationFromUser/{userName}/medications/{medicationId}
http://localhost:8080/medicationUsers/updateMedicationFromUser/{userName}/medications


MEDICATION
http://localhost:8080/medications/{id}
http://localhost:8080/medications/save
http://localhost:8080/medications/update/{id}
http://localhost:8080/medications/delete/{id}
http://localhost:8080/medications/create_medication
http://localhost:8080/medications/remove_medications/{medicationUserId}
http://localhost:8080/medications/update/{medicationUserId}
http://localhost:8080/medications/get_medication_by_name/{name}
http://localhost:8080/medications/get_all_medications

