import Chart from "../../components/chart/Chart";
import FeaturedInfo from "../../components/featuredInfo/FeaturedInfo";
import WidgetLg from "../../components/widgetLg/WidgetLg";
import WidgetSm from "../../components/widgetSm/WidgetSm";
import "./home.scss";
import React from "react";

const Home: React.FC = () => {
  return (
    <div className="homePage">
      <FeaturedInfo />
      <div className="widgets">
        <Chart
            dataKey={'Patient'}
            grid={true}
            title={'Статистика приемов'}
        />
      </div>
      <div className="widgets">
          <WidgetSm />
          <WidgetLg />
      </div>
    </div>
  );
};

export default Home;
