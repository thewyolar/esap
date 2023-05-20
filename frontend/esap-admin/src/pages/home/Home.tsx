import "./home.scss";
import React from "react";
import Chart from "../../components/chart/Chart";
import FeaturedInfo from "../../components/featuredInfo/FeaturedInfo";
import WidgetLg from "../../components/widgetLg/WidgetLg";
import WidgetSm from "../../components/widgetSm/WidgetSm";
import WidgetRight from "../../components/widgetRight/WidgetRight";

const Home: React.FC = () => {
  return (
    <div className="homePage">
      <FeaturedInfo />
      <div className="widgets">
        <Chart
            grid={true}
            title={'Статистика приемов'}
        />
        <WidgetRight />
      </div>
      <div className="widgets">
          <WidgetSm />
          {/*<WidgetLg /> //TODO: Для регистратора не отображать*/}
      </div>
    </div>
  );
};

export default Home;
