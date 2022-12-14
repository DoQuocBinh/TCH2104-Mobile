import { IonButton, IonContent, IonDatetime, IonHeader, IonInput, IonItem, IonLabel, IonList, IonPage, IonPopover, IonRouterLink, IonTitle, IonToolbar } from '@ionic/react';
import { useState } from 'react';

interface Product{
  name: string,
  productionDate: string | undefined
}

const Home: React.FC = () => {
  const [name,setName] = useState<string>('')
  const [productionDate,setProductionDate] = useState<string>()
  const [allProducts,setAllProducts] = useState<Product[]>([])

  const dateSelectedHandler = (e:any) =>{
    const selectedDate = new Date(e.detail.value)
    setProductionDate(selectedDate.toLocaleDateString("en-GB"))
  }
  const saveHandler = ()=>{
    const newProduct : Product = {'name':name,'productionDate':productionDate}
    //append the new product to the current list - allProducts
    setAllProducts([newProduct,...allProducts])
    
  }
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="warning">
          <IonTitle>Product management</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonItem>
          <IonLabel position='floating'>Product Name</IonLabel>
          <IonInput onIonChange={e=>setName(e.detail.value!)}></IonInput>
        </IonItem>
        <IonItem>
          <IonLabel position='floating'>Production Date</IonLabel>
          <IonInput value={productionDate} id='productionDate'></IonInput>
          <IonPopover keepContentsMounted={true} trigger='productionDate' event='click'>
            <IonDatetime onIonChange={e=>dateSelectedHandler(e)}></IonDatetime>
          </IonPopover>
        </IonItem>
        <IonButton onClick={saveHandler} expand='block' class='ion-margin'>Save</IonButton>
        {allProducts &&
          <IonList>
            {allProducts.map(c=>
              <IonItem key={c.name}>
                <IonLabel>{c.name} - {c.productionDate}</IonLabel>
              </IonItem>
              )}
          </IonList>
        }
        <IonRouterLink routerLink='/setting'>go setting</IonRouterLink>
      </IonContent>
    </IonPage>
  );
};

export default Home;
