# EVM
Electronic Voting Machine
It's a program to register new Candidates and cast votes to get a winner
In this project there is 5 Api as below : 
http://localhost:8080/entercandidate?name=Archit
This Api is use to register a name for elections
http://localhost:8080/castvote?name=Archit
This Api use to cast a vote for a candidate
http://localhost:8080/countvote?name=Archit
This Api use to count vote for a specific candidate
http://localhost:8080/listvote
This Api is use to get the list of candidate name and their vote counts
http://localhost:8080/getwinner
This Api is Use to get the winner of the election
