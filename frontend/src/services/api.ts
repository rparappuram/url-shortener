import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});

export const shortenURL = async (longURL: string) => {
  const response = await api.post("/shorten", longURL, {
    headers: { "Content-Type": "text/plain" },
  });
  return response.data;
};
