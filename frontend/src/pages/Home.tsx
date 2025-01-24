import { Navbar } from "../components/Navbar";
import { HeroSection } from "../components/HeroSection";
import { WhyChoose } from "../components/WhyChoose";
import { Footer } from "../components/Footer";

export default function Home() {
  return (
    <div className="h-screen w-screen flex flex-col">
      <Navbar />
      <main className="flex-grow">
        <HeroSection />
        <WhyChoose />
      </main>
      <Footer />
    </div>
  );
}
