import { createContext, useContext, useState, useEffect } from "react";

interface AuthContextType {
  user: string | null;
  token: string | null;
  signUpInfo: (user: string, token: string) => void;
  loginInfo: (user: string, token: string) => void;
  logoutInfo: () => void;
  isAuthenticated: boolean;
  isLoading: boolean;
  pendingURL: string | null;
  setPendingURL: (url: string | null) => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [user, setUser] = useState<string | null>(null);
  const [token, setToken] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [pendingURL, setPendingURL] = useState<string | null>(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    const storedToken = localStorage.getItem("token");
    if (storedUser && storedToken) {
      setUser(storedUser);
      setToken(storedToken);
    }

    setIsLoading(false);
  }, []);

  const signUpInfo = (user: string, token: string) => {
    loginInfo(user, token);
  };

  const loginInfo = (user: string, token: string) => {
    setUser(user);
    setToken(token);
    localStorage.setItem("user", user);
    localStorage.setItem("token", token);
  };

  const logoutInfo = () => {
    setUser(null);
    setToken(null);
    localStorage.removeItem("user");
    localStorage.removeItem("token");
  };

  const isAuthenticated = user !== null && token !== null;

  return (
    <AuthContext.Provider
      value={{
        user,
        token,
        signUpInfo,
        loginInfo,
        logoutInfo,
        isAuthenticated,
        isLoading,
        pendingURL,
        setPendingURL,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};
