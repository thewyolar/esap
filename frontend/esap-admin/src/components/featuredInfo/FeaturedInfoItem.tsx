import React from 'react';
import "./featuredInfo.scss";

interface FeaturedInfoProps {
  title: string,
  description: string | number;
  sub: string | null;
}

const FeaturedInfoItem: React.FC<FeaturedInfoProps> = ({title, description, sub}) => {
  return (
    <div className="item">
      <span className="title">{title}</span>
      <div>
        <span className="desc">{description}</span>
      </div>
      <span className="sub">{sub}</span>
    </div>
  );
};

export default FeaturedInfoItem;