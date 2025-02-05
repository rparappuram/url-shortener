import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Card,
  CardHeader,
  CardTitle,
  CardDescription,
  CardContent,
  CardFooter,
} from "@/components/ui/card";
import { RegisterLogo } from "@/components/RegisterLogo";
import { PasswordInput } from "@/components/PasswordInput";
import { Mail, Lock } from "lucide-react";
import { Link } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Here you would typically handle the login logic
    console.log("Login attempt with:", { email, password });
  };

  return (
    <div className="h-screen w-screen flex items-center justify-center bg-gray-100 flex-col">
      <RegisterLogo />
      <Card className="w-full max-w-md">
        <CardHeader className="space-y-1">
          <CardTitle className="text-2xl font-bold flex items-center justify-center">
            Log in and start sharing
          </CardTitle>
          <CardDescription className="text-center">
            Don't have an account?{" "}
            <Link to="/signup" className="text-blue-600 hover:underline">
              Sign up
            </Link>
          </CardDescription>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="space-y-2">
              <Label htmlFor="email">Email</Label>
              <div className="relative">
                <Mail className="absolute left-2 top-2.5 h-4 w-4 text-gray-500" />
                <Input
                  id="email"
                  type="email"
                  placeholder="john@example.com"
                  className="pl-8"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>
            </div>
            <div className="space-y-2">
              <Label htmlFor="password">Password</Label>
              <div className="relative">
                <Lock className="absolute left-2 top-2.5 h-4 w-4 text-gray-500" />
                <PasswordInput
                  id="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              {/* move Forgot your password to the right */}
              <div className="relative text-right">
                <p className="text-sm text-gray-600">
                  <Link to="#" className="text-blue-600 hover:underline">
                    Forgot your password?
                  </Link>
                </p>
              </div>
            </div>
            <Button type="submit" className="w-full">
              Log In
            </Button>
          </form>
        </CardContent>
        <CardFooter className="flex justify-center">
          <p className="text-xs text-gray-600">
            By logging in with an account, you agree to our{" "}
            <Link to="#" className="text-blue-600 hover:underline">
              Terms of Service
            </Link>{" "}
            and{" "}
            <Link to="#" className="text-blue-600 hover:underline">
              Privacy Policy
            </Link>
            .
          </p>
        </CardFooter>
      </Card>
    </div>
  );
}
