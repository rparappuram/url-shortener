import { FeatureCard } from "./FeatureCard";
import { Link } from "lucide-react";

export function WhyChoose() {
  return (
    <section className="bg-gray-50 py-16">
      <div className="container mx-auto px-4">
        <h3 className="text-2xl font-bold mb-8 text-center">
          Why Choose {import.meta.env.VITE_APP_NAME}?
        </h3>
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
  );
}