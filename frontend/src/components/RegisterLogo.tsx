import { Link as LinkIcon } from "lucide-react";
import { Link } from "react-router-dom";

export function RegisterLogo() {
  return (
    <Link to="/">
      <h1
        className="text-4xl font-bold flex items-center mb-10 cursor-pointer"
        onClick={() => (window.location.href = "/")}
      >
        <LinkIcon className="w-10 h-10 mr-2" />
        {import.meta.env.VITE_APP_NAME}
      </h1>
    </Link>
  );
}
