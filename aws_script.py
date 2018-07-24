import os
# os.system('search.username=username')
# os.system('mvn clean package -DskipTests=true')



user_email = 'abc@abc.com'
user_pass = 'testtesttest'
fan1 = '@11111'
fan2 = '@22222'
fan3 = '@33333'
comment_text='Text23456'
commnt_icon_x_location = '1024444'
commnt_icon_y_location = '204788'
# print("sed -i '' 's/search.username=pratik/search.username="+username+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/valid.email=pratikchachpara@gmail.com/valid.email="+user_email+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/valid.password=pratikmusicly/valid.password="+user_pass+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/username.fan1=@ashbare22/username.fan1="+fan1+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/username.fan2=@piggylover4122007/username.fan2="+fan2+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/username.fan3=@bihhh_im_windex/username.fan3="+fan3+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/comment.text=Hello123/comment.text="+comment_text+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/Pixel.comment.icon_x=1007/Pixel.comment.icon_x="+commnt_icon_x_location+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/Pixel.comment.icon_y=1145/Pixel.comment.icon_y="+commnt_icon_y_location+"/g' src/test/resources/configuration.properties")
os.system('mvn clean package -DskipTests=true')
os.system('git stash')