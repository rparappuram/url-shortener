import { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Pencil, Trash2 } from "lucide-react";
import { Navbar } from "@/components/Navbar";
import {
  shortenURL,
  getAllURLsForUser,
  deleteURL,
} from "@/services/urlService";

interface ShortenedLink {
  id: number;
  longURL: string;
  shortURL: string;
  createdAt: string;
  clickCount: number;
}

export default function Dashboard() {
  const [newUrl, setNewUrl] = useState("");
  const [links, setLinks] = useState<ShortenedLink[]>([]);

  const fetchLinks = async () => {
    const response = await getAllURLsForUser();
    setLinks(response);
  };

  useEffect(() => {
    fetchLinks();
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await shortenURL(newUrl);
    setNewUrl("");
    fetchLinks();
  };

  const handleEdit = (id: number) => {
    alert("TODO: Implement edit functionality for " + id);
  };

  const handleDelete = async (shortURL: string) => {
    await deleteURL(shortURL);
    fetchLinks();
  };

  return (
    <div className="h-screen w-screen bg-gray-100 flex flex-col">
      <Navbar />

      <main className="flex-grow container mx-auto px-4 py-8">
        <Card className="mb-8">
          <CardHeader>
            <CardTitle>Create a New Short Link</CardTitle>
            <CardDescription>
              Enter a long URL to generate a shortened link
            </CardDescription>
          </CardHeader>
          <CardContent>
            <form onSubmit={handleSubmit} className="flex space-x-2">
              <div className="flex-grow">
                <Label htmlFor="newUrl" className="sr-only">
                  URL to shorten
                </Label>
                <Input
                  id="newUrl"
                  type="text"
                  placeholder="Enter your long URL here"
                  value={newUrl}
                  onChange={(e) => setNewUrl(e.target.value)}
                  required
                />
              </div>
              <Button type="submit">Shorten</Button>
            </form>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Your Shortened Links</CardTitle>
            <CardDescription>
              Manage and track your shortened URLs
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>Original URL</TableHead>
                  <TableHead>Short URL</TableHead>
                  <TableHead>Clicks</TableHead>
                  <TableHead>Created At</TableHead>
                  <TableHead>Actions</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {links &&
                  links.map((link) => (
                    <TableRow key={link.id}>
                      <TableCell className="font-medium">
                        {link.longURL}
                      </TableCell>
                      {/* change hover color */}
                      <TableCell>
                        <a
                          href={`${import.meta.env.VITE_API_URL}/${link.shortURL}`}
                          target="_blank"
                          rel="noopener noreferrer"
                          className="text-blue-600 hover:underline"
                        >
                          {`${import.meta.env.VITE_API_URL}/${link.shortURL}`}
                        </a>
                      </TableCell>
                      <TableCell>{link.clickCount}</TableCell>
                      <TableCell>{link.createdAt}</TableCell>
                      <TableCell>
                        <div className="flex space-x-2">
                          <Button
                            variant="secondary"
                            size="sm"
                            onClick={() => handleEdit(link.id)}
                          >
                            <Pencil className="h-4 w-4" />
                          </Button>
                          <Button
                            variant="secondary"
                            size="sm"
                            onClick={() => handleDelete(link.shortURL)}
                          >
                            <Trash2 className="h-4 w-4" />
                          </Button>
                        </div>
                      </TableCell>
                    </TableRow>
                  ))}
              </TableBody>
            </Table>
          </CardContent>
        </Card>
      </main>

      <footer className="bg-white border-t">
        <div className="container mx-auto px-4 py-6 text-center text-sm text-gray-600">
          © 2025 {import.meta.env.VITE_APP_NAME}. All rights reserved.
        </div>
      </footer>
    </div>
  );
}
