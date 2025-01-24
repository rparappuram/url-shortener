import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Link } from "lucide-react"

export default function Home() {
    return (
      <div className="h-screen w-screen flex flex-col">
        <header className="border-b">
          <div className="container mx-auto px-4 py-4 flex justify-between items-center">
            <h1 className="text-2xl font-bold flex items-center">
              <Link className="w-6 h-6 mr-2" />
              {import.meta.env.VITE_APP_NAME}
            </h1>
            <nav>
              <Button variant="secondary">Login</Button>
              <Button>Sign Up</Button>
            </nav>
          </div>
        </header>
  
        <main className="flex-grow">
          <section className="container mx-auto px-4 py-16 text-center">
            <h2 className="text-4xl font-bold mb-4">Shorten Your Links</h2>
            <p className="text-xl mb-8">Create short, memorable links in seconds</p>
            <div className="flex max-w-md mx-auto">
              <Input type="url" placeholder="Paste your long URL here" className="flex-grow" />
              <Button type="submit" className="ml-2">
                Shorten
              </Button>
            </div>
          </section>
  
          <section className="bg-gray-50 py-16">
            <div className="container mx-auto px-4">
              <h3 className="text-2xl font-bold mb-8 text-center">Why Choose {import.meta.env.VITE_APP_NAME}?</h3>
              <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
                <FeatureCard
                  icon={<Link className="w-12 h-12" />}
                  title="Easy to Use"
                  description="Shorten links with just a click of a button"
                />
                <FeatureCard
                  icon={<Link className="w-12 h-12" />}
                  title="Customizable"
                  description="Create custom short links for your brand"
                />
                <FeatureCard
                  icon={<Link className="w-12 h-12" />}
                  title="Analytics"
                  description="Track clicks and monitor link performance"
                />
              </div>
            </div>
          </section>
        </main>
  
        <footer className="border-t">
          <div className="container mx-auto px-4 py-6 text-center text-sm text-gray-600">
            © 2025 {import.meta.env.VITE_APP_NAME}. All rights reserved.
          </div>
        </footer>
      </div>
    )
  }

function FeatureCard({ icon, title, description }: { icon: React.ReactNode; title: string; description: string }) {
    return (
      <div className="bg-white p-6 rounded-lg shadow-md text-center">
        <div className="mb-4 flex justify-center">{icon}</div>
        <h4 className="text-xl font-semibold mb-2">{title}</h4>
        <p className="text-gray-600">{description}</p>
      </div>
    )
  }