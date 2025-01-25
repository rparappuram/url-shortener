import { Button } from "@/components/ui/button";
import { Link } from "lucide-react";

export function Navbar() {
  return (
    <header className="border-b">
      <div className="container mx-auto px-4 py-4 flex justify-between items-center">
        <h1 className="text-2xl font-bold flex items-center">
          <Link className="w-6 h-6 mr-2" />
          {import.meta.env.VITE_APP_NAME}
        </h1>
        <nav className="flex items-center">
          <Button variant="secondary" className="mr-4">
            Login
          </Button>
          <Button>Sign Up</Button>
        </nav>
      </div>
    </header>
  );
}