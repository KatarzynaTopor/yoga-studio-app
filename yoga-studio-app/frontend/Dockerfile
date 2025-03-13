# Use Node.js as the base image
FROM node:18-alpine

# Set working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install

# Copy the full frontend source
COPY . .

# Build the React app
RUN npm run build

# Expose frontend port
EXPOSE 3000

# Start React app
CMD ["npm", "start"]
