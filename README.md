# AndroidLibrarySample

AndroidLibrarySample is a project that demonstrates the process of publishing modules as libraries and using them in other projects. It showcases the usage of Retrofit and Compose to fetch data from an API and display it in an Android application.

## Project Structure

The project consists of the following modules:

- dependent-library-one: A library module that serves as a dependency for other modules.
- dependent-library-two: Another library module that depends on dependent-library-one.
- main-library-published: The main library module that combines dependent-library-one and dependent-library-two.
- app: A sample application module that utilizes the main-library-published as a dependency.

## Prerequisites

Before getting started, make sure you have the following prerequisites:

- Android Studio (latest version)
- Git

## Getting Started

To get started with the AndroidLibrarySample project, follow the steps below:

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/AndroidLibrarySample.git
