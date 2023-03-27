import './featuredInfo.scss'

import { ArrowDownward, ArrowUpward } from '@mui/icons-material'

const FeaturedInfo = () => {
  return (
    <div className='featuredInfo'>
      <div className='item'>
        <span className='title'>Записей на сегодня</span>
        <div>
          <span className='money'>100</span>
          {/*<span className='moneyRate'>*/}
          {/*   -11 <ArrowDownward className='icon negative'/>*/}
          {/*</span>*/}
        </div>
        {/*<span className='sub'>По сравнению со вчера</span>*/}
      </div>

      <div className='item'>
        <span className='title'>Lorem ipsum</span>
        <div>
          <span className='money'>Lorem ipsum</span>
          {/*<span className='moneyRate'>*/}
          {/*   -1,4 <ArrowDownward className='icon negative' />*/}
          {/*</span>*/}
        </div>
        {/*<span className='sub'>Lorem ipsum</span>*/}
      </div>

      <div className='item'>
        <span className='title'>Lorem ipsum</span>
        <div>
          <span className='money'>Lorem ipsum</span>
          {/*<span className='moneyRate'>*/}
          {/*   +12,4 <ArrowUpward className='icon' />*/}
          {/*</span>*/}
        </div>
        {/*<span className='sub'>Lorem ipsum</span>*/}
      </div>
    </div>
  )
}

export default FeaturedInfo
