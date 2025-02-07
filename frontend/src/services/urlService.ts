import api from "./api";

interface ShortenResponse {
  shortUrl: string;
}

export const shortenURL = async (longURL: string): Promise<ShortenResponse> => {
  const response = await api.post<ShortenResponse>("/url", { longURL });
  return response.data;
};

export const getAllURLsForUser = async () => {
  const response = await api.get("/urls");
  console.log(response);
  return response.data;
};

export const deleteURL = async (shortURL: string) => {
  await api.delete("/url", { data: { shortURL } });
};
