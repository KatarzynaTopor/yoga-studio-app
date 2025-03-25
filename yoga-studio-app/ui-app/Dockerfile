# Używamy obrazu Node.js zamiast Nginx
FROM node:18

WORKDIR /app

# Kopiujemy package.json i instalujemy zależności
COPY package.json package-lock.json ./
RUN npm install

# Kopiujemy cały kod
COPY . .
EXPOSE 80
# Uruchamiamy tryb developerski
CMD ["npm", "start"]
