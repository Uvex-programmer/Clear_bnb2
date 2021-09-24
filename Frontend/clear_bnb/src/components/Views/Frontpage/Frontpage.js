import { useEffect } from 'react';

export default function FrontPage() {
  useEffect(() => {
    fetch('/api/properties')
      .then(async (res) => await JSON.parse(await res.json()))
      .then((data) => console.log(data));
  }, []);

  return (
    <>
      <div className='frontpage'>
        <p>HOMEPAGE</p>
      </div>
    </>
  );
}
