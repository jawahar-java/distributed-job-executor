# 🏗 Distributed Job Executor - Architecture

## Overview

The Distributed Job Executor is a microservices-based backend application that distributes asynchronous jobs across multiple worker nodes.

The project follows a **Controller–Worker Architecture**, where the Controller manages jobs and Workers execute them independently.

---

# High Level Architecture

```
                           Client
                              │
                              │
                    Submit Job Request
                              │
                              ▼
              +--------------------------------+
              |       Controller Service       |
              +--------------------------------+
              |                                |
              | Job APIs                       |
              | Worker Registry                |
              | Heartbeat Manager              |
              | Dashboard                      |
              | Round Robin Scheduler          |
              +---------------+----------------+
                              │
                    PostgreSQL Database
                              │
        +---------------------+---------------------+
        |                     |                     |
        ▼                     ▼                     ▼
+----------------+   +----------------+   +----------------+
| Worker Service |   | Worker Service |   | Worker Service |
|    Worker-1    |   |    Worker-2    |   |    Worker-3    |
+----------------+   +----------------+   +----------------+
        │                     │                     │
        │                     │                     │
 Heartbeat             Heartbeat             Heartbeat
 Poll Jobs             Poll Jobs             Poll Jobs
 Execute Jobs          Execute Jobs          Execute Jobs
```

---

# Components

## Controller Service

Responsible for

- Job Submission
- Worker Registration
- Heartbeat Monitoring
- Job Scheduling
- Round Robin Load Balancing
- Retry Logic
- Dead Letter Queue
- Dashboard APIs

---

## Worker Service

Responsible for

- Registering itself
- Sending Heartbeats
- Polling Jobs
- Executing Jobs
- Updating Job Status

---

## PostgreSQL

Stores

### Jobs

- Job Information
- Retry Count
- Worker Assignment
- Status

### Workers

- Registration Details
- Heartbeat Timestamp
- Current Status
- Total Executed Jobs

---

# Component Responsibilities

## Client

- Submit Jobs
- Query Job Status
- View Dashboard

---

## Controller

- Persist Jobs
- Assign Jobs
- Monitor Workers
- Detect Offline Workers
- Retry Failed Jobs

---

## Worker

- Execute Assigned Jobs
- Report Execution Status
- Send Heartbeats

---

# Sequence Diagram

## Job Submission

```
Client
  │
  │ POST /jobs
  ▼
Controller
  │
  │ Save Job
  ▼
Database
```

---

## Worker Registration

```
Worker
   │
   │ POST /workers/register
   ▼
Controller
   │
   │ Save Worker
   ▼
Database
```

---

## Heartbeat

```
Worker
   │
   │ POST /workers/heartbeat
   ▼
Controller
   │
Update LastHeartbeat
   ▼
Database
```

---

## Job Assignment

```
Scheduler

      │

      ▼

Find SUBMITTED Jobs

      │

      ▼

Round Robin Load Balancer

      │

      ▼

Find Available Worker

      │

      ▼

Assign Job

      │

      ▼

Update Database
```

---

## Job Execution

```
Worker

     │

Poll Next Job

     │

     ▼

Controller

     │

Return ASSIGNED Job

     │

     ▼

Worker Executes Job

     │

     ▼

POST /jobs/status

     │

     ▼

Controller Updates Status
```

---

# Job Lifecycle

```
                SUBMITTED
                     │
                     ▼
                ASSIGNED
                     │
                     ▼
                 RUNNING
                /       \
               /         \
              ▼           ▼
         SUCCESS       FAILED
                           │
                     Retry Count++
                           │
               +-----------+-----------+
               |                       |
      Retry Count < 3       Retry Count >= 3
               |                       |
               ▼                       ▼
         SUBMITTED              DEAD LETTER
```

---

# Worker Lifecycle

```
Application Starts

        │

        ▼

Register Worker

        │

        ▼

REGISTERED

        │

        ▼

Heartbeat Every 30 Seconds

        │

        ▼

IDLE

        │

        ▼

Assigned Job

        │

        ▼

BUSY

        │

        ▼

Execution Complete

        │

        ▼

IDLE
```

---

# Database Design

## workers

```
id (UUID)

worker_name

host

port

status

active_jobs

total_jobs_executed

registered_at

last_heartbeat

updated_at
```

---

## jobs

```
id (UUID)

job_type

payload

status

retry_count

worker_id

assigned_at

created_at

updated_at
```

---

# Round Robin Load Balancer

Jobs are distributed equally across available workers.

Example

```
Worker-1

↓

Worker-2

↓

Worker-3

↓

Worker-1

↓

Worker-2
```

Benefits

- Equal Load Distribution
- Simple Implementation
- No Worker Starvation

---

# Retry Mechanism

When execution fails

```
Retry Count++

↓

Retry < 3

↓

SUBMITTED
```

Otherwise

```
Retry >= 3

↓

DEAD LETTER
```

This prevents infinite retries.

---

# Offline Worker Detection

Every worker sends heartbeat every 30 seconds.

Controller checks

```
Current Time

-

Last Heartbeat
```

If threshold exceeded

```
Worker Status

↓

OFFLINE
```

---

# Design Decisions

## Why Microservices?

Separates Controller responsibilities from Worker responsibilities.

Advantages

- Independent deployment
- Horizontal scaling
- Better maintainability

---

## Why Polling?

Workers poll for jobs instead of the Controller pushing work.

Advantages

- Simpler networking
- Firewall friendly
- Easier fault tolerance

---

## Why UUID?

Every Job and Worker receives a globally unique identifier.

Advantages

- No collision
- Distributed friendly
- Easy database sharding

---

## Why Round Robin?

Chosen because

- Fair distribution
- Low overhead
- Predictable behavior

Future enhancement:

Weighted Round Robin or Least Loaded Worker.

---

# Scalability

Current

```
1 Controller

↓

Multiple Workers
```

Future

```
Load Balancer

↓

Multiple Controllers

↓

Shared PostgreSQL

↓

100+ Workers
```

---

# Future Enhancements

- Docker Compose
- Kubernetes Deployment
- Apache Kafka
- RabbitMQ
- Redis Queue
- Prometheus Metrics
- Grafana Dashboard
- JWT Authentication
- AWS Deployment
- Horizontal Autoscaling
- Distributed Locks
- Event Driven Architecture

---

# Conclusion

This project demonstrates the implementation of a distributed backend system using Spring Boot and PostgreSQL. It showcases key backend engineering concepts including microservices, worker coordination, heartbeat monitoring, load balancing, retry handling, dead letter queues, scheduling, and asynchronous job execution.