import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export function HeroSection() {
  return (
    <section className="container mx-auto px-4 py-16 text-center h-full flex flex-col justify-center">
      <h2 className="text-4xl font-bold mb-4">Shorten Your Links</h2>
      <p className="text-xl mb-8">Create short, memorable links in seconds</p>
      <div className="flex max-w-2xl mx-auto w-full">
        <Input type="url" placeholder="Paste your long URL here" className="flex-grow" />
        <Button type="submit" className="ml-2">
          Shorten
        </Button>
      </div>
    </section>
  );
}