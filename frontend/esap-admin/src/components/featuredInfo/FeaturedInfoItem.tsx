import React from 'react';
import "./featuredInfo.scss";

interface FeaturedInfoProps {
  title: string,
  description: string | number;
}

const FeaturedInfoItem: React.FC<FeaturedInfoProps> = ({title, description}) => {
  return (
    <div className="item">
      <span className="title">{title}</span>
      <div>
        <span className="desc">{description}</span>
      </div>
    </div>
  );
};

export default FeaturedInfoItem;