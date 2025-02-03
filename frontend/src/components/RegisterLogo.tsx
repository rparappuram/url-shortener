import { Link } from "lucide-react";

export function RegisterLogo() {
  return (
    <a href="/">
      <h1
        className="text-4xl font-bold flex items-center mb-10 cursor-pointer"
        onClick={() => (window.location.href = "/")}
      >
        <Link className="w-10 h-10 mr-2" />
        {import.meta.env.VITE_APP_NAME}
      </h1>
    </a>
  );
}
