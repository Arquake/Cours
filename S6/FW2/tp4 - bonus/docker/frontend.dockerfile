FROM node:18

# Set working directory
WORKDIR /app

# Copy package files
COPY ../frontend/package.json ../frontend/package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the Angular files
COPY ../frontend/ ./

# Expose Angular development port
EXPOSE 4200

CMD ["npm", "start"]
