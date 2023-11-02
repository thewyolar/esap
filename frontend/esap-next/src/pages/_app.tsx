import '@/styles/globals.scss';
import type { AppProps } from 'next/app';
import Topbar from "@/components/topbar/Topbar";
import Sidebar from "@/components/sidebar/Sidebar";
import {useRouter} from "next/router";
import {useEffect} from "react";
import {TokenStorageService} from "@/service/auth/TokenStorageService";

const App = ({ Component, pageProps }: AppProps) => {
  const router = useRouter();
  const tokenStorageService = new TokenStorageService();
  const allowedRoutes = ['/login', '/register', '/password/reset'];
  const token = tokenStorageService.getToken();
  const isAllowedRoute = allowedRoutes.includes(router.pathname);

  useEffect(() => {
    if (!token && !isAllowedRoute) {
      router.push('/login');
    } else if (token && isAllowedRoute) {
      router.push('/dashboard');
    }
  }, [token, isAllowedRoute, router]);

  if (token || isAllowedRoute) {
    if (isAllowedRoute) {
      return <Component {...pageProps} />;
    } else {
      return (
        <>
          <Topbar />
          <div className="app">
            <Sidebar />
            <Component {...pageProps} />
          </div>
        </>
      );
    }
  } else {
    return null;
  }
};

export default App;