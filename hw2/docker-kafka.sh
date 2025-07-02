#!/bin/bash

# This script automates the installation of Docker and Docker Compose (V2)
# and then attempts to run a docker-compose.yml file for Kafka.

echo "--- Updating system packages ---"
sudo yum update -y
echo "--- System update complete ---"

echo "--- Installing Docker Engine ---"
[cite_start]sudo yum install -y docker [cite: 1]
[cite_start]sudo systemctl start docker [cite: 1]
echo "--- Docker Engine installed and started ---"

echo "--- Adding ec2-user to the docker group ---"
[cite_start]sudo usermod -a -G docker ec2-user [cite: 1]
echo "--- User added to docker group. Re-logging is required for group changes to take effect ---"

# Apply group changes for the current session without requiring a full re-login
# This command effectively creates a new shell session with updated group memberships.
newgrp docker

echo "--- Installing Docker Compose V2 plugin ---"
DOCKER_CONFIG=${DOCKER_CONFIG:-$HOME/.docker}
mkdir -p "$DOCKER_CONFIG/cli-plugins"
curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o "$DOCKER_CONFIG/cli-plugins/docker-compose"
chmod +x "$DOCKER_CONFIG/cli-plugins/docker-compose"
echo "--- Docker Compose V2 plugin installed ---"

echo "--- Verifying Docker Compose installation ---"
[cite_start]docker compose version [cite: 1]
if [ $? -ne 0 ]; then
    echo "Error: Docker Compose V2 verification failed. Please check the installation."
    exit 1
fi
echo "--- Docker Compose verification successful ---"

# --- Important Note: Your docker-compose.yml file MUST be in the same directory as this script. ---
# --- Ensure your EC2 instance has sufficient memory (e.g., t3.medium or larger) ---
# --- Ensure your docker-compose.yml has correct ADVERTISED_LISTENERS for your EC2 Public IP/DNS ---

echo "--- Running docker-compose.yml ---"
[cite_start]docker compose up -d [cite: 1]
if [ $? -ne 0 ]; then
    echo "Error: Docker Compose failed to start containers. Check logs for details."
    exit 1
fi
echo "--- Docker Compose containers started in detached mode ---"

echo "Script finished. Use 'docker ps' to check running containers and 'docker compose logs' for detailed logs."
