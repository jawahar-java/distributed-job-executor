# Distributed Job Executor System

## Overview
This project implements a distributed job execution platform where jobs are
submitted via an API, assigned to worker nodes, executed asynchronously, and
tracked through their lifecycle.

The system is designed to mirror real-world backend execution platforms with
a focus on correctness, reliability, and failure handling.

## Core Concepts
- Controller–Worker architecture
- Explicit job lifecycle state machine
- Retry and timeout handling
- Execution audit and observability
- Concurrency-safe processing

## High-Level Architecture
- Job Controller Service
- Worker Service (multiple instances)
- Persistent storage (relational database)
- Logical asynchronous job queue

## Job Lifecycle
SUBMITTED → ASSIGNED → RUNNING → SUCCESS  
FAILED → RETRY → DEAD_LETTER

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL / PostgreSQL
- Docker

## Status
🚧 In progress — actively building
