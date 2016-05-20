db.profs.aggregate([
    { $unwind: '$vorlesungen' },
    { $group: 
        {
            _id: {
                    Name:  '$name',
                    sws: { $sum: '$vorlesungen.sws' }
            },
            students: {$addToSet: '$vorlesungen.studenten'}      
        }
    },
    { $unwind:  '$students'},
    { $project: { count: { $size: '$students' }}},    
    { $group:
        {
            _id: {
                ProfName:  '$_id.Name'
            },
                Studenten: { $sum: '$count'},
                SWS: { $sum: '$_id.sws'}
        }        
    },
])