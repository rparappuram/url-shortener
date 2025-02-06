import api from "./api";

interface AuthResponse {
  token: string;
  username: string;
}

export const signUp = async (
  username: string,
  password: string,
): Promise<AuthResponse> => {
  const response = await api.post<AuthResponse>("/auth/signup", {
    username,
    password,
  });
  return response.data;
};

export const login = async (
  username: string,
  password: string,
): Promise<AuthResponse> => {
  const response = await api.post<AuthResponse>("/auth/login", {
    username,
    password,
  });
  return response.data;
};

export const logout = async (): Promise<void> => {
  await api.post("/auth/logout");
};
