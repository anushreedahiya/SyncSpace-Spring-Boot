## SyncSpace
### Introduction:- 
In today’s fast-paced digital world, effective project management is crucial for timely delivery and productivity. Traditional project management systems require significant human oversight, including task allocation, tracking progress, and ensuring the quality of work. With the rise of artificial intelligence, we can automate many of these processes to reduce human effort and 
improve efficiency.
SyncSpace is an innovative AI-powered task management system designed to revolutionize project execution. In SyncSpace, both the project manager and workers are AI-driven, capable of autonomously handling tasks from start to finish. Users simply submit high-level tasks, and the system intelligently manages the decomposition, execution, and consolidation of subtasks. 
By combining Spring Boot, Spring AI with Google Gemini, JWT-based security, Firebase authentication, and React.js frontend, SyncSpace provides a secure, scalable, and user-friendly platform that simulates real-world project management while leveraging cutting-edge AI capabilities. 

### Project Objectives:- 
● Minimize human involvement in task decomposition and execution. 
● Provide AI-driven task allocation and monitoring to optimize efficiency. 
● Enable users to track progress and receive high-quality outputs without micromanaging. 
● Incorporate role-based access and secure authentication to maintain privacy and control. 
● Allow extensibility for multiple AI workers to operate in parallel, simulating a real-world 
collaborative environment.

### Technical Implementation:- 
● Backend: Spring Boot 3+, REST API-based architecture. 
● Database: MySQL for persistent storage of users, tasks, subtasks, workers, and history logs. 
● Security: 
○ JWT-based authentication for secure API access. 
○ Firebase Google authentication for user login. 
○ Role-based access control (User / Admin / AI). 
● AI Integration: 
○ Spring AI with Google Gemini for Project Manager and Worker AI logic. 
○ PM AI handles task decomposition and merging subtasks. 
○ Worker AI executes subtasks autonomously. 
● Frontend: React.js application for user interaction, task submission, and tracking. 
● Validation: Spring Validation annotations (@Valid, @NotBlank, @Size) to ensure data integrity.
