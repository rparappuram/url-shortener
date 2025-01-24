// src/services/api.ts
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api", // Replace with your AWS backend URL after deployment
});

export const shortenURL = async (longURL: string) => {
  const response = await api.post("/shorten", longURL, {
    headers: { "Content-Type": "text/plain" },
  });
  return response.data;
};