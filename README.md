# DocuMind AI

DocuMind AI is a Retrieval-Augmented Generation (RAG) based chatbot that lets you upload PDF documents and ask questions about their content. Answers are generated strictly from the information found inside your uploaded documents — if the answer isn't in the document, the assistant tells you so instead of guessing.

## Features

- **PDF Upload** — Upload one or more PDF documents to build a knowledge base.
- **Natural Language Q&A** — Ask questions in plain English about your documents.
- **Grounded Answers** — Responses are generated only from retrieved content, reducing hallucinations.
- **Document Library** — View a list of all PDFs currently indexed in the system.
- **Local LLM Inference** — Runs on Ollama, so models can be served locally without relying on third-party APIs.
- **Simple Web UI** — Clean, responsive interface for uploading documents and chatting.

## Tech Stack

- **Backend:** Java, Spring Boot, Spring AI
- **LLM Runtime:** Ollama
- **Frontend:** HTML, CSS, JavaScript
- **Architecture:** Retrieval-Augmented Generation (RAG) with vector embeddings for semantic search

## How It Works

1. **Upload** — A PDF is parsed and split into smaller text chunks.
2. **Embed & Store** — Each chunk is converted into vector embeddings and stored in a vector store.
3. **Retrieve** — When a question is asked, the most semantically relevant chunks are retrieved.
4. **Answer** — The LLM generates a response using only the retrieved context, or indicates that the answer isn't available in the uploaded documents.

## Getting Started

### Prerequisites

- Java 17+
- Maven or Gradle
- [Ollama](https://ollama.com/) installed and running locally


### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/documind-ai.git
   cd documind-ai
   ```

2. Configure your `application.properties` (or `application.yml`) with your Ollama base URL and model name.

3. Build and run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

4. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```

5. Upload a PDF and start asking questions.


## Contributing

Contributions, issues, and feature requests are welcome. Feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
