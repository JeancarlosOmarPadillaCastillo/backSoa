export interface AsignationActTeenComplete {
  activitiesResponseDto: {
    id_activities:number;
    name : string ;
    description : string ;
    date : string ;
    duration : string ;
    location  : string ;
    type_pronacej:string;
    type_soa:string;
    active : string ;
  };

  teenResponseDto: {
    id_teenager : number ;
    name : string ;
    father_last_name : string ;
    mother_last_name : string ;
    document_type : string ;
    document_number : number ;
    cellphone : number ;
    address : string ;
  };

  transaccionalActTeen: {
    id_act_teen: number;
    description: string;
    start_date: string;
    duration: string;
    notes: string;
    participation_status: string;
    active: string;
    id_activities: number;
    id_teenager: number;
  };
}
